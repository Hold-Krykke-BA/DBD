package proj.generated.valuedomains;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.abstractica.edma.valuedomains.IMetaValueDomain;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import org.abstractica.edma.valuedomains.userinput.ITerminal;
import org.abstractica.edma.valuedomains.userinput.ValueDomainInput;
import proj.generated.LearnEDMA;
import proj.generated.valuedomains.external.EDMA_ExternalConstraints;
import proj.generated.valuedomains.impl.CoursesBuilderImpl;
import proj.generated.valuedomains.impl.CoursesImpl;

/**
 * The representation of a value from the value domain: Courses
 */
public abstract class Courses implements Comparable<Courses>, Iterable<Course>
{
    protected static final IMetaValueDomain edma_domain = LearnEDMA.environment.getValueDomainDefinitions().getValueDomain(7);



    /**
     * Get a value from a terminal
     * @param terminal  The terminal to get the value from
     * @return          The Courses from the terminal
     */
    public static Courses fromTerminal(ITerminal terminal)
    {
        ValueDomainInput vdi = new ValueDomainInput(terminal, EDMA_ExternalConstraints.instance);
        return new CoursesImpl(vdi.getValue(edma_domain));
    }

    /**
     * Get a value from its string representation
     * @param s  The String to parse
     * @return   The Courses from the string representation
     */
    public static Courses fromString(String s) throws InvalidValueException
    {
        Object res = edma_domain.valueFromString(s, EDMA_ExternalConstraints.instance);
        return new CoursesImpl(res);
    }

    /**
     * Reads and validates a value from a stream
     * @param in  A data input interface for the stream to read from
     * @return    The Courses read from the stream
     */
    public static Courses fromStream(DataInput in) throws IOException, InvalidValueException
    {
        Object res = edma_domain.readValue(in, EDMA_ExternalConstraints.instance);
        return new CoursesImpl(res);
    }

    /**
     * Reads a value from a stream without validating the value
     * @param in  A data input interface for the stream to read from
     * @return    The Courses read from the stream
     */
    public static Courses fromStreamNoValidate(DataInput in) throws IOException
    {
        Object res = edma_domain.readValueNoValidate(in);
        return new CoursesImpl(res);
    }

    /**
     * Starts creation of a new Courses
     * @return  Builder interface to build the list
     */
    public static CoursesBuilder begin()
    {
        return new CoursesBuilderImpl();
    }



    /**
     * Writes this value to a stream
     * @param out  Interface to data output stream
     */
    public abstract void toStream(DataOutput out) throws IOException;

    /**
     * Returns the size of this list
     * @return  the size of this list
     */
    public abstract int size();

    /**
     * Returns the element on a given index in this list
     * @param index  The index of the element to be returned
     * @return       the element on the given index in this list
     */
    public abstract Course get(int index);


    /**
     * Interface to create a list
     */
    public interface CoursesBuilder
    {

        /**
         * Adds an element to the list
         * @param course  The element to be added to the list
         * @return        An interface to the builder for chaining method calls
         */
        public CoursesBuilder add(Course course);

        /**
         * Builds the list with the added elements
         * @return  The builded list
         */
        public Courses end();

    }

}
