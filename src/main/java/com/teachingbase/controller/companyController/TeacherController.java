package com.teachingbase.controller.companyController;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.BaseFile;
import com.teachingbase.service.BaseFileService;
import com.teachingbase.service.BaseService;
import com.teachingbase.service.BaseStudentService;
import com.teachingbase.service.ClassStudentService;
import com.teachingbase.util.ExcelUtil;
import com.teachingbase.util.SessionContextUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/teacher")
@RequiresRoles("teacher")
@Controller
public class TeacherController {

    @Autowired
    public BaseService baseService;
    @Autowired
    public ClassStudentService classStudentService;
    @Autowired
    public BaseStudentService baseStudentService;
    @Autowired
    public BaseFileService baseFileService;

    @RequestMapping("/studentAttendance")
    public String studentAttendance(Model model) {
        String teacherId = SessionContextUtil.getCurrentUser().getUsername();
        List<Base> baseList = baseService.getBaseByTeacherId(teacherId);
        model.addAttribute("baseList",baseList);
        return "company/student_attendance";
    }

    @RequestMapping("/registryManage")
    public String registryManage(String baseId,Model model) {
        Base base = classStudentService.getStudentOfBaseByBaseId(baseId);
        int count = baseStudentService.getCountStudentOfBase(baseId);
        if (base == null){
            base = baseService.getBaseByBaseId(baseId);
        }
        BaseFile baseFile = baseFileService.getBaseFileByBaseId(baseId);
        model.addAttribute("base",base);
        model.addAttribute("baseFile",baseFile);
        model.addAttribute("count",count);
        return "company/registry_manage";
    }
    @RequestMapping(value = "/enrolledStudents",method = RequestMethod.GET)
    public String enrolledStudent(String baseId, Model model){
        Base base = classStudentService.getStudentOfBaseByBaseId(baseId);
        int count = baseStudentService.getCountStudentOfBase(baseId);
        if (base == null){
            base = baseService.getBaseByBaseId(baseId);
        }
        model.addAttribute("base",base);
        model.addAttribute("count",count);
        return "company/enrolledStudents";
    }

    @ResponseBody
    @RequestMapping(value = "/enrolledStudents",method = RequestMethod.POST)
    public Base enrolledStudentPost(String baseId,int curr,int limit,Model model){
        Map map = new HashMap();
        curr = (curr-1)*limit;
        map.put("baseId",baseId);
        map.put("curr",curr);
        map.put("limit",limit);
        Base base = classStudentService.getStudentOfBaseByBaseIdAndPage(map);
        int count = baseStudentService.getCountStudentOfBase(baseId);
        if (base == null){
            base = baseService.getBaseByBaseId(baseId);
        }
        model.addAttribute("count",count);
        return base;
    }

    @ResponseBody
    @RequestMapping("/updateReportStatus")
    public boolean updateReportStatus(String reportStatus,String studentId){
        Map map = new HashMap();
        map.put("reportStatus",reportStatus);
        map.put("studentId",studentId);
        boolean result = baseStudentService.updateBaseReportStatus(map);
        return result;
    }

    @RequestMapping("/loadExcel")
    public void loadExcel(HttpServletResponse response,String baseId) throws IOException {
        Base base = classStudentService.getStudentOfBaseByBaseId(baseId);
        List studentList= base.getStudentsOfBaseList();
        String[] title = {"学号","姓名","日期1","日期2","..."};
        String baseName = base.getBaseName();
        String teacherName = SessionContextUtil.getCurrentUser().getName();
        HSSFWorkbook workbook = ExcelUtil.generateExcel(title, studentList, baseName, teacherName);
        String fileName = baseName+".xls";
        //下载后的excel名称可以为中文
        String downloadFileName =new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        String headStr ="attachment; filename=\"" + downloadFileName +"\"";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", headStr);
        OutputStream out = response.getOutputStream();
        workbook.write(out);
    }

    @ResponseBody
    @RequestMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file")MultipartFile file,String baseId, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return "<script>alert('上传失败，请选择文件。');location.href='/teacher/registryManage?baseId="+baseId+"'</script>";
        }
        String fileName = file.getOriginalFilename();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String uploadDate = simpleDateFormat.format(new Date());

        String suffix  = fileName.substring(fileName.lastIndexOf(".") + 1);//后缀名
        if(!(suffix.contains("xls")||suffix.contains("xlsx"))){
            return "<script>alert('文件类型不合格，请重新上传。');location.href='/teacher/registryManage?baseId="+baseId+"'</script>";
        }

        String suffixDate = "";
        if (fileName.indexOf("_")<=0){
            fileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+uploadDate+".xls";//文件名字加上上传日期
        }else{
            suffixDate =  fileName.substring(fileName.lastIndexOf("_") + 1);
            if (!suffixDate.equals(uploadDate+".xls")){
                String substring = fileName.substring(0,fileName.lastIndexOf("_"));
                fileName=substring+"_"+uploadDate+".xls";
            }
        }

        BaseFile baseFile = new BaseFile();
        baseFile.setBaseId(baseId);
        baseFile.setFilePath(fileName);
        //上传文件service
        boolean result = baseFileService.addBaseFile(baseFile,file);
        if (result){
            return "<script>alert('上传成功。');location.href='/teacher/registryManage?baseId="+baseId+"';</script>";
        }else {
            return "<script>alert('上传失败，请重新上传');location.href='/teacher/registryManage?baseId="+baseId+"';</script>";
        }

        //上传成功
       /* BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));
        out.flush();
        out.close();*/
    }

    @RequestMapping("/loadLasteExcel")
    public void loadLasteExcel(String baseId,HttpServletResponse response) throws IOException {
        BaseFile baseFile = baseFileService.getBaseFileByBaseId(baseId);
        String fileName = baseFile.getFilePath();
        String downloadFileName =new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        String headStr ="attachment; filename=\"" + downloadFileName +"\"";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", headStr);
        String filePath = ExcelUtil.PATH+fileName;
        File file = new File(filePath);
        if (file.exists()){
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream outputStream = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/baseDetails")
    public String getBaseDetails(String baseId,Model model){
        Base base = baseService.getBaseByBaseId(baseId);
        model.addAttribute("base",base);
        return "company/base_details";
    }

    @RequestMapping("/dailyAttendance")
    public String dailyAttendance() {
        return "company/student_attendance";
    }
}
