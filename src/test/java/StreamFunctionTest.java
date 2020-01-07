import com.alibaba.fastjson.JSONObject;
import com.django.springboot2.utils.ExcelUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName StreamFunctionTest
 * @Description 流式函数测试
 * @Author liulongyun
 * @Date 2020/1/6 9:27
 * @Version 1.0.0
 **/
public class StreamFunctionTest {

    public static void main(String[] args) {

        try {
            String filePath = "e:/streamtest.xlsx";

            List<String> heads = new ArrayList<>();

            heads.add("grade");
            heads.add("class");
            heads.add("term");
            heads.add("studentId");
            heads.add("curriculumId");
            heads.add("curriculum");
            heads.add("score");
            heads.add("note");

            List<JSONObject> dataList = ExcelUtils.importExcel(filePath, heads);


            // 求和
            double sum = dataList.stream().filter(stu -> {
                return stu.get("studentId").equals("05080001");
            }).mapToDouble(stu -> {
                return stu.getDouble("score");
            }).sum();

            // 求平均值
            double average = dataList.stream().filter(stu -> {
                return stu.get("studentId").equals("05080001");
            }).mapToDouble(stu -> {
                return stu.getDouble("score");
            }).average().getAsDouble();

            // 分组
            Map<String, List<JSONObject>> groupedMap = dataList.stream().collect(Collectors.groupingBy(stu -> stu.getString("studentId")));


            System.out.println("05080001学号学生的所有成绩总和："+sum);
            System.out.println("05080001学号学生的平均成绩："+average);
            System.out.println("分组数据："+JSONObject.toJSONString(groupedMap));




        } catch (Exception e) {
            e.printStackTrace();


        }


    }






}
