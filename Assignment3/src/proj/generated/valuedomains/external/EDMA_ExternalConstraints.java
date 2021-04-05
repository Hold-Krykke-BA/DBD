package proj.generated.valuedomains.external;

import org.abstractica.edma.util.Pair;
import org.abstractica.edma.valuedomains.IExternalConstraints;
import proj.generated.valuedomains.Ects;
import proj.generated.valuedomains.impl.EctsImpl;
import proj.usercode.valueconstraints.ects.DivisibleByFive;

/**
 * 
 */
public class EDMA_ExternalConstraints implements IExternalConstraints
{
    public static final IExternalConstraints instance = new EDMA_ExternalConstraints();



    /**
     * Constructor
     */
    public EDMA_ExternalConstraints()
    {
        
    }

    /**
     * check external constraints
     * @param edma_obj    The value object to check
     * @param edma_index  Value domain index
     * @return            A pair of the index of the first constraint that is
     *                    violated and the error message or <tt>null</tt> if no
     *                    constraints are violated
     */
    public Pair<Integer, String> checkConstraints(Object edma_obj, int edma_index)
    {
        switch(edma_index)
        {
            case 3:
            {
                Ects ects = new EctsImpl(edma_obj);
                String edma_reason;
                edma_reason = DivisibleByFive.checkDivisibleByFive(ects);
                if(edma_reason != null) return new Pair<Integer, String>(0, edma_reason);
                return null;
            }
            default :
                throw new RuntimeException("Internal Error!");
        }
    }
}
