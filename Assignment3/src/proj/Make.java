package proj;

import org.abstractica.edma.generator.EdmaGenerator;

public class Make {
    public static void main(String[] args) {
        String projectDir = "C:\\Users\\regsa\\OneDrive\\Desktop\\Softwareudvikling\\assignments\\DBD\\DBD\\Assignment3";
        EdmaGenerator generator = new EdmaGenerator("Assignment3", projectDir + "/edmasrc", projectDir + "/src", "proj");
        generator.make();
    }
}
