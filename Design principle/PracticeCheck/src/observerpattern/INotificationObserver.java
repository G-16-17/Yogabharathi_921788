package observerpattern;

public interface INotificationObserver {

	String name = "";

	public void onServerDown();

	public String getName();
}
