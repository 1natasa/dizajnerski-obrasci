package paint.serialization;

public interface Strategy {

	public void save(String path);
	public void load(String path);
}
