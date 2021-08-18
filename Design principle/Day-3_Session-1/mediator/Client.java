package mediator;

public class Client {

	public static void main(String[] args) {
		ChatMediator chatMediator = new ChatMediator();
		BasicUser bu = new BasicUser("Yoga", chatMediator);
		PremiumUser pu1 = new PremiumUser("Sound", chatMediator);
		PremiumUser pu2 = new PremiumUser("Pradeep", chatMediator);
		chatMediator.AddUser(pu1);
		chatMediator.AddUser(pu2);
		String name = bu.getName();

		bu.SendMessage(name, "Hi All");
	}
}