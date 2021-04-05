package proj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.abstractica.edma.metamodel.impl.ValueDomainBuilder;
import org.abstractica.edma.valuedomains.impl.Constraint;
import org.json.*;
import proj.generated.valuedomains.*;
import proj.generated.valuedomains.impl.CourseBuilderImpl;
import proj.generated.valuedomains.impl.CoursesBuilderImpl;
import proj.generated.valuedomains.impl.CoursesImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Assignment {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("C:\\Users\\regsa\\OneDrive\\Desktop\\Softwareudvikling\\assignments\\DBD\\DBD\\Assignment3\\Data\\jsondata.json"), StandardCharsets.US_ASCII);
            JSONObject jsonData = new JSONObject(content);
            System.out.println(jsonData);

            //Task 1 start
            jsonData.getJSONObject("student").getString("name");

            Name studentName = Name.create(jsonData.getJSONObject("student").getString("name"));
            Age age = Age.create( jsonData.getJSONObject("student").getInt("age"));

            JSONArray arr = jsonData.getJSONArray("courses");

            CoursesBuilderImpl coursesBuilder = new CoursesBuilderImpl();
            Courses courses = new CoursesImpl(null);
            for (int i = 0; i < arr.length(); i++){
                JSONObject jobj = arr.getJSONObject(i);
                Ects ects = Ects.create(jobj.getInt("ects"));
                Id id = Id.create(jobj.getInt("id"));
                Name courseName = Name.create(jobj.getString("name"));

                Course course = Course.create().id(id).name(courseName).ects(ects);
                courses = coursesBuilder.add(course).end();
            }
            Active active = Active.create(jsonData.getBoolean("active"));

            Student student = Student.create().name(studentName).age(age);

            As3 as3 = As3.create().student(student).courses(courses).active(active);
            System.out.println(as3);
            //Task 1 End

            //Task 2 Start
            System.out.println(as3.toString());


            //Task 2 End
            System.out.println(as3ToString(as3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String as3ToString(As3 as3) {
        return "{\"student\": { \"name\": " + as3.student().name() + ", \"age\":" + as3.student().age() + "}," +
                "\"courses\": [{\"id\":" + as3.courses().get(0).id() + ", \"name\":" + as3.courses().get(0).name() + ", \"ects\":" + as3.courses().get(0).ects() + "}," +
                "{\"id\":" + as3.courses().get(1).id() + ", \"name\":" + as3.courses().get(1).name() + ", \"ects\":" + as3.courses().get(1).ects() + "}]," +
                "\"active\":" + as3.active().value() + "}";
    }
}
