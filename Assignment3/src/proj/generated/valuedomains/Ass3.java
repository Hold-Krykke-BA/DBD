package proj.generated.valuedomains;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.abstractica.edma.valuedomains.IMetaValueDomain;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import org.abstractica.edma.valuedomains.userinput.ITerminal;
import org.abstractica.edma.valuedomains.userinput.ValueDomainInput;
import proj.generated.LearnEDMA;
import proj.generated.valuedomains.Active;
import proj.generated.valuedomains.Courses;
import proj.generated.valuedomains.Student;
import proj.generated.valuedomains.external.EDMA_ExternalConstraints;
import proj.generated.valuedomains.impl.Ass3BuilderImpl;
import proj.generated.valuedomains.impl.Ass3Impl;

/**
 * The representation of a value from the value domain: Ass3
 */
public abstract class Ass3 implements Comparable<Ass3>
{
    protected static final IMetaValueDomain edma_domain = LearnEDMA.environment.getValueDomainDefinitions().getValueDomain(8);



    /**
     * Get a value from a terminal
     * @param terminal  The terminal to get the value from
     * @return          The Ass3 from the terminal
     */
    public static Ass3 fromTerminal(ITerminal terminal)
    {
        ValueDomainInput vdi = new ValueDomainInput(terminal, EDMA_ExternalConstraints.instance);
        return new Ass3Impl(vdi.getValue(edma_domain));
    }

    /**
     * Get a value from its string representation
     * @param s  The String to parse
     * @return   The Ass3 from the string representation
     */
    public static Ass3 fromString(String s) throws InvalidValueException
    {
        Object res = edma_domain.valueFromString(s, EDMA_ExternalConstraints.instance);
        return new Ass3Impl(res);
    }

    /**
     * Reads and validates a value from a stream
     * @param in  A data input interface for the stream to read from
     * @return    The Ass3 read from the stream
     */
    public static Ass3 fromStream(DataInput in) throws IOException, InvalidValueException
    {
        Object res = edma_domain.readValue(in, EDMA_ExternalConstraints.instance);
        return new Ass3Impl(res);
    }

    /**
     * Reads a value from a stream without validating the value
     * @param in  A data input interface for the stream to read from
     * @return    The Ass3 read from the stream
     */
    public static Ass3 fromStreamNoValidate(DataInput in) throws IOException
    {
        Object res = edma_domain.readValueNoValidate(in);
        return new Ass3Impl(res);
    }

    /**
     * Starts creation of a new Ass3
     * @return  Builder interface to set the student field
     */
    public static Ass3BuilderStudent create()
    {
        return new Ass3BuilderImpl();
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
     * Builder interface for setting the student field of Ass3
     */
    public interface Ass3BuilderStudent
    {

        /**
         * sets the student field.
         * @param student  The value to assign to the student field
         * @return         Builder interface for setting the courses field
         */
        public Ass3BuilderCourses student(Student student);

    }



    /**
     * Builder interface for setting the courses field of Ass3
     */
    public interface Ass3BuilderCourses
    {

        /**
         * sets the courses field.
         * @param courses  The value to assign to the courses field
         * @return         Builder interface for setting the active field
         */
        public Ass3BuilderActive courses(Courses courses);

    }



    /**
     * Builder interface for setting the active field of Ass3
     */
    public interface Ass3BuilderActive
    {

        /**
         * sets the active field.
         * @param active  The value to assign to the active field
         * @return        The created Ass3 value
         */
        public Ass3 active(Active active);

        /**
         * sets the active field.
         * @param active  The value to assign to the active field
         * @return        The created Ass3 value
         */
        public Ass3 active(Boolean active);

    }

}
