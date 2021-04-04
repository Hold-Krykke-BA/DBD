package proj.generated.valuedomains.impl;

import java.util.ArrayList;
import org.abstractica.edma.valuedomains.IValueInstance;
import proj.generated.valuedomains.Course;
import proj.generated.valuedomains.Courses;
import proj.generated.valuedomains.Courses.CoursesBuilder;

/**
 * 
 */
public class CoursesBuilderImpl implements CoursesBuilder
{
    private ArrayList<Object> valueList;



    /**
     * Constructor
     */
    public CoursesBuilderImpl()
    {
        valueList = new ArrayList<Object>();
    }

    /**
     * Adds an element to the list
     * @param course  The element to be added to the list
     * @return        An interface to the builder for chaining method calls
     */
    public CoursesBuilder add(Course course)
    {
        if(course == null) throw new NullPointerException();
        valueList.add(((IValueInstance) course).edma_getValue());
        return this;
    }

    /**
     * Builds the list with the added elements
     * @return  The builded list
     */
    public Courses end()
    {
        Object[] res = new Object[valueList.size()];
        int pos = 0;
        for(Object o : valueList)
        {
            res[pos++] = o;
        }
        return new CoursesImpl(CoursesImpl.edma_create(res));
    }
}
