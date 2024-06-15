package application;

import javafx.event.Event;
import javafx.event.EventType;

public class AccountEvent extends Event {

    public static final EventType<AccountEvent> ACCOUNT_CHANGED =
            new EventType<>(Event.ANY, "ACCOUNT_CHANGED");

    public AccountEvent() {
        super(ACCOUNT_CHANGED);
    }
}