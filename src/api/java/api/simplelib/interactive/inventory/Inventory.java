package api.simplelib.interactive.inventory;

import api.simplelib.Var;
import net.minecraft.item.ItemStack;
import net.simplelib.interactive.inventory.InventoryManagerImpl;

/**
 * This interface indicates that this contains extra inventory space.
 *
 * @author ci010
 */
public interface Inventory
{
	/**
	 * Factory method to provides extra inventory infomation by some methods of {@link InventoryManagerImpl}
	 *
	 * @param manager The inventory Manager.
	 */
	void provideInventory(Manager manager);

	/**
	 * @author ci010
	 */
	interface Manager
	{
		/**
		 * This is the easy solution. You directly assign a slot to a specific position.
		 * <p>To point this slot in {@link api.simplelib.interactive.process.Process},
		 * use the {@link SlotInfo} returned by this to create a {@link Var} with {@link ItemStack}
		 * which link to this slot.
		 * </p>
		 *
		 * @param slotId The id of the slot.
		 * @param x      The x position of the slot.
		 * @param y      The y position of the slot.
		 * @return {@link SlotInfo}, The info of this slot.
		 * @see api.simplelib.interactive.process.ProcessPipeline.Factory#newStack(SlotInfo)
		 */
		SlotInfo newSingletonSlot(String slotId, int x, int y);

		/**
		 * Create a space of slot.
		 * <p>
		 * In this space, the slots will be allocated according to row * column shape.
		 * The first slot will be on (x,y); the second will be on just right of the first, and so on.</p>
		 * <p>In this situation, if you want to use {@link SpaceInfo#get(int, int)} to point a specific
		 * slot, and create {@link Var} by {@link SlotInfo}.</p>
		 *
		 * @param spaceId The id of the space.
		 * @param x       The left x position of all the slots.
		 * @param y       The top y position of all the slots.
		 * @param row     The numbers of row.
		 * @param column  The numbers of column.
		 * @return {@link SpaceInfo}, The info of this space.
		 * @see api.simplelib.interactive.process.ProcessPipeline.Factory#newStack(SlotInfo)
		 */
		SpaceInfo newSlotSpace(String spaceId, int x, int y, int row, int column);
	}
}