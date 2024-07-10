package net.xephea.luckyblocks;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public interface ILuckyBlockFactory
{
	// Get a lucky block in inventory format
	// Amount - The number of lucky blocks in the item stack
	// Luck - The luck value of the blocks
	public ItemStack GetLuckyBlockItem(int amount, int luck);
	
	// Attach the given luck value to the given block
	public Block MakeLuckyBlock(Block block, int luck);
	
	// Attach the luck value of the given lucky block to the given block
	public Block MakeLuckyBlock(Block block, ItemStack item);
	
	// Verify whether the given block is a lucky block
	public boolean IsLuckyBlock(Block block);
	
	// Verify whether the given item is a lucky block
	public boolean IsLuckyBlock(ItemStack items);
	
	// Get the luck value of the given lucky block
	// Returns 0 if is not a lucky block
	// Verify using IsLuckyBlock() before use
	public int GetLuck(ItemStack luckyBlockItem);

	// Get the luck value of the given lucky block
	// Returns 0 if is not a lucky block
	// Verify using IsLuckyBlock() before use
	public int GetLuck(Block luckyBlock);
}
