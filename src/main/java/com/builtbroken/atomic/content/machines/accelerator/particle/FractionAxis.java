package com.builtbroken.atomic.content.machines.accelerator.particle;

/**
 * Created by Dark(DarkGuardsman, Robert) on 2019-05-29.
 */
public class FractionAxis
{

    //Whole numbers (1,2,3)
    private int wholeNumbers;

    //Top of the fraction
    private int numerator;
    //Bottom of the fraction
    private int denominator; //TODO add a IntSupplier to allow scaling, keep the field to check for change in scale

    public FractionAxis()
    {
    }

    public FractionAxis(int denominator)
    {
        this.denominator = denominator;
    }

    /**
     * Called to clear the stored value
     */
    public void clear()
    {
        wholeNumbers = 0;
        numerator = 0;
    }

    /**
     * Called to set the value of the fraction
     * <p>
     * Keep in mind this will round off
     * to a fraction inside the limit of the
     * {@link #denominator}
     */
    public void set(double value)
    {
        clear();
        add(value);
    }

    /**
     * Called to add to the fraction
     * <p>
     * Keep in mind this will round off
     * to a fraction inside the limit of the
     * {@link #denominator}
     *
     * @param add - value to add
     */
    public void add(double add)
    {
        if(add > 0)
        {
            //Get whole numbers from add
            int whole = (int) Math.round(add);
            add -= whole;

            //Add to shole
            wholeNumbers += whole;

            //Get partial
            numerator += (int) Math.round(add * denominator);

            //Reduce partial if above
            if (numerator >= denominator)
            {
                whole = numerator / denominator;
                numerator -= whole * denominator;
                wholeNumbers += whole;
            }
        }
    }

    /**
     * Get the fraction as a double
     *
     * @return
     */
    public double get()
    {
        return wholeNumbers + getDecimal();
    }

    /**
     * Whole numbers of the fraction
     *
     * @return
     */
    public int getWhole()
    {
        return wholeNumbers;
    }

    /**
     * Top of the fraction
     *
     * @return
     */
    public int getNumerator()
    {
        return numerator;
    }

    /**
     * Bottom of the fraction
     *
     * @return
     */
    public int getDenominator()
    {
        return denominator;
    }

    /**
     * Gets the fraction as a double
     *
     * @return
     */
    public double getDecimal()
    {
        return numerator / (double) denominator;
    }
}
