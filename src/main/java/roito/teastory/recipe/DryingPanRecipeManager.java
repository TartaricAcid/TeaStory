package roito.teastory.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class DryingPanRecipeManager implements IRecipeManager<ITeaMakingRecipe>
{
    @Override
    public boolean equal(ITeaMakingRecipe recipe1, ITeaMakingRecipe recipe2)
    {
        return recipe1.getStep3() == recipe2.getStep3();
    }

    @Override
    public void add(ITeaMakingRecipe recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public void remove(ITeaMakingRecipe recipe)
    {
        java.util.Iterator<ITeaMakingRecipe> iter = recipes.iterator();
        while (iter.hasNext())
        {
            if (iter.next().equals(recipe))
            {
                iter.remove();
                return;
            }
        }
    }

    @Override
    public Collection<ITeaMakingRecipe> getRecipes()
    {
        return recipes;
    }

    @Override
    public <T> ITeaMakingRecipe getRecipe(T... inputs)
    {
        if (inputs[0] == null || !(inputs[0] instanceof ItemStack))
            return null;
        for (ITeaMakingRecipe r : recipes)
        {
            if (r.getStep3().isItemEqual((ItemStack) inputs[0]) && r.getStep3().getCount() <= ((ItemStack) inputs[0]).getCount())
                return r;
        }
        return null;
    }

    private static ArrayList<ITeaMakingRecipe> recipes = new ArrayList<>();

}
