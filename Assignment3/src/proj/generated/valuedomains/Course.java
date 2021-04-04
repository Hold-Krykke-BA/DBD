package proj.generated.valuedomains;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.abstractica.edma.valuedomains.IMetaValueDomain;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import org.abstractica.edma.valuedomains.userinput.ITerminal;
import org.abstractica.edma.valuedomains.userinput.ValueDomainInput;
import proj.generated.LearnEDMA;
import proj.generated.valuedomains.Ects;
import proj.generated.valuedomains.Id;
import proj.generated.valuedomains.Name;
import proj.generated.valuedomains.external.EDMA_ExternalConstraints;
import proj.generated.valuedomains.impl.CourseBuilderImpl;
import proj.generated.valuedomains.impl.CourseImpl;

/**
 * The representation of a value from the value domain: Course
 */
public abstract class Course implements Comparable<Course>
{
    protected static final IMetaValueDomain edma_domain = LearnEDMA.environment.getValueDomainDefinitions().getValueDomain(6);



    /**
     * Get a value from a terminal
     * @param terminal  The terminal to get the value from
     * @return          The Course from the terminal
     */
    public static Course fromTerminal(ITerminal terminal)
    {
        ValueDomainInput vdi = new ValueDomainInput(terminal, EDMA_ExternalConstraints.instance);
        return new CourseImpl(vdi.getValue(edma_domain));
    }

    /**
     * Get a value from its string representation
     * @param s  The String to parse
     * @return   The Course from the string representation
     */
    public static Course fromString(String s) throws InvalidValueException
    {
        Object res = edma_domain.valueFromString(s, EDMA_ExternalConstraints.instance);
        return new CourseImpl(res);
    }

    /**
     * Reads and validates a value from a stream
     * @param in  A data input interface for the stream to read from
     * @return    The Course read from the stream
     */
    public static Course fromStream(DataInput in) throws IOException, InvalidValueException
    {
        Object res = edma_domain.readValue(in, EDMA_ExternalConstraints.instance);
        return new CourseImpl(res);
    }

    /**
     * Reads a value from a stream without validating the value
     * @param in  A data input interface for the stream to read from
     * @return    The Course read from the stream
     */
    public static Course fromStreamNoValidate(DataInput in) throws IOException
    {
        Object res = edma_domain.readValueNoValidate(in);
        return new CourseImpl(res);
    }

    /**
     * Starts creation of a new Course
     * @return  Builder interface to set the id field
     */
    public static CourseBuilderId create()
    {
        return new CourseBuilderImpl();
    }



    /**
     * Writes this value to a stream
     * @param out  Interface to data output stream
     */
    public abstract void toStream(DataOutput out) throws IOException;

    /**
     * returns the value of the field id
     * @return  The value of the field id
     */
    public abstract Id id();

    /**
     * returns the value of the field name
     * @return  The value of the field name
     */
    public abstract Name name();

    /**
     * returns the value of the field ects
     * @return  The value of the field ects
     */
    public abstract Ects ects();


    /**
     * Builder interface for setting the id field of Course
     */
    public interface CourseBuilderId
    {

        /**
         * sets the id field.
         * @param id  The value to assign to the id field
         * @return    Builder interface for setting the name field
         */
        public CourseBuilderName id(Id id);

        /**
         * sets the id field.
         * @param id  The value to assign to the id field
         * @return    Builder interface for setting the name field
         */
        public CourseBuilderName id(Integer id) throws InvalidValueException;

    }



    /**
     * Builder interface for setting the name field of Course
     */
    public interface CourseBuilderName
    {

        /**
         * sets the name field.
         * @param name  The value to assign to the name field
         * @return      Builder interface for setting the ects field
         */
        public CourseBuilderEcts name(Name name);

        /**
         * sets the name field.
         * @param name  The value to assign to the name field
         * @return      Builder interface for setting the ects field
         */
        public CourseBuilderEcts name(String name) throws InvalidValueException;

    }



    /**
     * Builder interface for setting the ects field of Course
     */
    public interface CourseBuilderEcts
    {

        /**
         * sets the ects field.
         * @param ects  The value to assign to the ects field
         * @return      The created Course value
         */
        public Course ects(Ects ects);

        /**
         * sets the ects field.
         * @param ects  The value to assign to the ects field
         * @return      The created Course value
         */
        public Course ects(Integer ects) throws InvalidValueException;

    }

}
