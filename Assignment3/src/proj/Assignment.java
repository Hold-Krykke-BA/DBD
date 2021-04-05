package proj;
import org.abstractica.edma.metamodel.impl.ValueDomainBuilder;
import org.abstractica.edma.valuedomains.impl.Constraint;
import org.json.*;
import proj.generated.valuedomains.*;
import proj.generated.valuedomains.impl.CourseBuilderImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Assignment {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("F:\\Developer\\Softwareudvikling\\1semester\\OrganisationsRepo\\DBD\\Assignment3\\Data\\jsondata.json"), StandardCharsets.US_ASCII);
            JSONObject jsonData = new JSONObject(content);
            System.out.println(jsonData);

            jsonData.getJSONObject("student").getString("name");

            Name studentName = Name.create(jsonData.getJSONObject("student").getString("name"));
            Age age = Age.create( jsonData.getJSONObject("student").getInt("age"));

            JSONArray arr = jsonData.getJSONArray("courses");

            ValueDomainBuilder vdb = new ValueDomainBuilder();
//            Courses courses;
            for (int i = 0; i < arr.length(); i++){

                vdb.newIntegerDomain("Id", null, 0, 20, null, false);

                Id id = Id.create(arr.getJSONObject(i).getInt("id"));
                Name courseName = Name.create(jsonData.getJSONObject("student").getString("name"));
                Ects ects = Ects.create(jsonData.getJSONObject("courses").getInt("ects"));

//              why can't i do this?
                CourseBuilderImpl cimpl = new CourseBuilderImpl();
//                cimpl.id(id).name(courseName).ects(ects);
                Course course = cimpl.id(id).name(courseName).ects(ects);
//              and this?

                Courses courses = Courses.begin().add(course).end();
            }

            Active active = Active.create(jsonData.getBoolean("active"));


            Student student = Student.create();

            As3 as3 = As3.create(student, courses, active);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
