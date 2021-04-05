package proj;

import org.abstractica.edma.generator.EdmaGenerator;

public class Make {
    public static void main(String[] args) {
        String projectDir = "F:\\Developer\\Softwareudvikling\\1semester\\OrganisationsRepo\\DBD\\Assignment3";
        EdmaGenerator generator = new EdmaGenerator("Assignment3Generator", projectDir + "/edmasrc", projectDir + "/src", "proj");
        generator.make();
    }
}
