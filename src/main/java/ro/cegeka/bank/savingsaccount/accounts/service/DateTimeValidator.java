package ro.cegeka.bank.savingsaccount.accounts.service;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateTimeValidator {

    public static final String NINE_AM = "09:00:00";
    public static final String SIX_PM = "18:00:00";


    private final Clock clock;

    public DateTimeValidator(Clock clock) {
        this.clock = clock;
    }


    public DateTimeValidator isWorkingDay() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(getInstantFromClock()));

        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if ((dayOfTheWeek >= Calendar.MONDAY) && (dayOfTheWeek <= Calendar.FRIDAY)) {
            return this;
        }

        throw new SavingsAccountCannotBeOpened("Accounts can't be open outside working days!");
    }

    private Instant getInstantFromClock() {
        return Instant.now(clock);
    }

    public DateTimeValidator isBetween9AMand6PM() {

        LocalTime now = getLocalTimeFromClock();

        if (now.isAfter(LocalTime.parse(NINE_AM)) && now.isBefore(LocalTime.parse(SIX_PM))) {
            return this;
        }

        throw new SavingsAccountCannotBeOpened("Accounts can't be open during business hours");
    }

    private LocalTime getLocalTimeFromClock() {
        return LocalTime.now(clock);
    }

    public DateTimeValidator and() {
        return this;
    }
}
