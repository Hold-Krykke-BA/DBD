package proj.usercode.valueconstraints.ects;

import proj.generated.valuedomains.Ects;

/**
 * This class is the implementation class for the Ects constraint
 * divisibleByFive
 * No description given
 */
public class DivisibleByFive
{



    /**
     * Checks the divisibleByFive constraint for the Ects value domain.
     * No description given
     * @param ects  The instance value to be checked
     * @return      the reason the constraint is violated, or <tt>null</tt> if
     *              the constraint is not violated
     */
    public static String checkDivisibleByFive(Ects ects)
    {
        // Implementation of constraint divisibleByFive
        // WARNING : Any code outside the following begin and end tags
        // will be lost when re-generation occurs.
        
        // EDMA_non-generated_code_begin

        if(ects.value() % 5 == 0){
            return null;
        }
        return "ECTS must be divisible by 5";
        
        // EDMA_non-generated_code_end
    }


}
