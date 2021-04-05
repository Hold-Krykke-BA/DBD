package proj.generated.valuedomains;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.abstractica.edma.valuedomains.IMetaValueDomain;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import org.abstractica.edma.valuedomains.userinput.ITerminal;
import org.abstractica.edma.valuedomains.userinput.ValueDomainInput;
import proj.generated.Assignment3;
import proj.generated.valuedomains.Active;
import proj.generated.valuedomains.Courses;
import proj.generated.valuedomains.Student;
import proj.generated.valuedomains.external.EDMA_ExternalConstraints;
import proj.generated.valuedomains.impl.As3BuilderImpl;
import proj.generated.valuedomains.impl.As3Impl;

/**
 * The representation of a value from the value domain: As3
 */
public abstract class As3 implements Comparable<As3>
{
    protected static final IMetaValueDomain edma_domain = Assignment3.environment.getValueDomainDefinitions().getValueDomain(8);



    /**
     * Get a value from a terminal
     * @param terminal  The terminal to get the value from
     * @return          The As3 from the terminal
     */
    public static As3 fromTerminal(ITerminal terminal)
    {
        ValueDomainInput vdi = new ValueDomainInput(terminal, EDMA_ExternalConstraints.instance);
        return new As3Impl(vdi.getValue(edma_domain));
    }

    /**
     * Get a value from its string representation
     * @param s  The String to parse
     * @return   The As3 from the string representation
     */
    public static As3 fromString(String s) throws InvalidValueException
    {
        Object res = edma_domain.valueFromString(s, EDMA_ExternalConstraints.instance);
        return new As3Impl(res);
    }

    /**
     * Reads and validates a value from a stream
     * @param in  A data input interface for the stream to read from
     * @return    The As3 read from the stream
     */
    public static As3 fromStream(DataInput in) throws IOException, InvalidValueException
    {
        Object res = edma_domain.readValue(in, EDMA_ExternalConstraints.instance);
        return new As3Impl(res);
    }

    /**
     * Reads a value from a stream without validating the value
     * @param in  A data input interface for the stream to read from
     * @return    The As3 read from the stream
     */
    public static As3 fromStreamNoValidate(DataInput in) throws IOException
    {
        Object res = edma_domain.readValueNoValidate(in);
        return new As3Impl(res);
    }

    /**
     * Starts creation of a new As3
     * @return  Builder interface to set the student field
     */
    public static As3BuilderStudent create()
    {
        return new As3BuilderImpl();
    }



    /**
     * Writes this value to a stream
     * @param out  Interface to data output stream
     */
    public abstract void toStream(DataOutput out) throws IOException;

    /**
     * returns the value of the field student
     * @return  The value of the field student
     */
    public abstract Student student();

    /**
     * returns the value of the field courses
     * @return  The value of the field courses
     */
    public abstract Courses courses();

    /**
     * returns the value of the field active
     * @return  The value of the field active
     */
    public abstract Active active();


    /**
     * Builder interface for setting the student field of As3
     */
    public interface As3BuilderStudent
    {

        /**
         * sets the student field.
         * @param student  The value to assign to the student field
         * @return         Builder interface for setting the courses field
         */
        public As3BuilderCourses student(Student student);

    }



    /**
     * Builder interface for setting the courses field of As3
     */
    public interface As3BuilderCourses
    {

        /**
         * sets the courses field.
         * @param courses  The value to assign to the courses field
         * @return         Builder interface for setting the active field
         */
        public As3BuilderActive courses(Courses courses);

    }



    /**
     * Builder interface for setting the active field of As3
     */
    public interface As3BuilderActive
    {

        /**
         * sets the active field.
         * @param active  The value to assign to the active field
         * @return        The created As3 value
         */
        public As3 active(Active active);

        /**
         * sets the active field.
         * @param active  The value to assign to the active field
         * @return        The created As3 value
         */
        public As3 active(Boolean active);

    }

}
