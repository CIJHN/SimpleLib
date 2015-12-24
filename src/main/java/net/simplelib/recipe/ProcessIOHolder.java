package net.simplelib.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author ci010
 */
public class ProcessIOHolder<Input extends Comparable<Input>, Output>
{
	private Map<Input, Output> pair = Maps.newHashMap();

	protected final void addRecipe(Input container, Output out)
	{
		pair.put(container, out);
	}

	public final Output getOutput(Input container)
	{
		for (Map.Entry<Input, Output> entry : pair.entrySet())
			if (container.compareTo(entry.getKey()) == 0)
				return entry.getValue();
		return null;
	}

	public final Map<Input, Output> getPair()
	{
		return ImmutableMap.copyOf(this.pair);
	}
}
