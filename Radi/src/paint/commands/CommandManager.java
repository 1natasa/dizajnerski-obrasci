package paint.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CommandManager extends Observable{
	private List<Command> commands;
	private int index;
	
	public CommandManager()
	{
		commands= new ArrayList<Command>();
		index=-1;
	}
	
	
	public void addCommand(Command command)
	{
		//ako je indeks manji od ukupnog broja komandi znaci da smo radili neke undo
		//primer sa salvetom hheheh :D
		if(index<commands.size()-1)
		{
			for(int i=commands.size()-1; i>index; i--)
			{
				commands.remove(i);
			}
		}
		commands.add(command);
		index++;
		
		setChanged();
		notifyObservers();
		
	}
	
	public boolean canRedo()
	{
		//ako je indeks na comandi manjoj od poslednje onda moze Redo 
		return index < commands.size()-1;
	}
	
	public boolean canUndo()
	{
		//kad nemam nista u nizu , ne moze undo
		return index >-1;
	}
	
	public Command getCommandForUndo()
	{
		return this.commands.get(index);
		
	}
	
	public Command getCommandForRedo()
	{
		return this.commands.get(index+1);
		
	}
	
	public void incrementIndex()
	{
		index++;
		setChanged();
		notifyObservers();
	}
	
	public void decrementIndex()
	{
		index--;
		setChanged();
		notifyObservers();
	}
	
	public List<Command> getAllCommands()
	{
		return commands;
	}
}
