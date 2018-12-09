package com.entities;

import com.events.criterias.Criteria;
import com.events.criterias.age.*;
import com.events.criterias.boughtTickets.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

/**
 * Event works before every booking. If there are events created and the user is valid for them then the ticket cost is reducing.
 *
 * @see com.events.criterias There are all criterias for filtering users
 */
@Table
@Entity
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @Getter
    @Setter
    private String condition;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Transient
    private static List<Event> events = new LinkedList<>();

    public Event(String condition , Date date) {
        this.condition = condition;
        this.endDate = date;
    }

    /**
     * Calculates the cost of the ticket.
     *
     * @param booking User's booking that must be checked before this finish
     * @return The ticket cost
     */
    public double reduce(Booking booking) {
        if (parse(booking)) {
            double result = booking.getSession().getCost();
            String[] parsed = condition.split(",");
            switch (parsed[parsed.length - 1]) {
                case "!":
                    result -= Double.parseDouble(parsed[parsed.length - 2]);
                    break;
                case "%":
                    if (Double.parseDouble(parsed[parsed.length - 2]) > 100)
                        throw new IllegalArgumentException("Cannot be more than 100 %");
                    result -= (result * Double.parseDouble(parsed[parsed.length - 2])) / 100;
                    break;
                default:
                    throw new IllegalArgumentException("The command is created wrong. Find the error in creating of the command.");
            }
            return result;
        }
        return booking.getSession().getCost();
    }

    /**
     * Map for containing criterias as text and criterias as objects those can check the criterias
     */
    private static final Map<String, Criteria> conditions = new HashMap<String, Criteria>() {{
        put("AGE<", ageLessCriteria.getInstance());
        put("AGE>", ageMoreCriteria.getInstance());
        put("AGE==", ageEqualsCriteria.getInstance());
        put("AGE!=", ageNotEqualsCriteria.getInstance());
        put("AGE<=", ageLessOrEqualsCriteria.getInstance());
        put("AGE>=", ageMoreOrEqualsCriteria.getInstance());
        put("TOTAL>", boughtTicketsMoreCriteria.getInstance());
        put("TOTAL>=", boughtTicketsMoreOrEqualsCriteria.getInstance());
    }};

    /**
     * Parses the condition and checks all criterias in the system. If there is something available the return true. Otherwise returns false
     *
     * @param booking User's booking
     * @return true if all of the criterias return true. Otherwise returns false
     */
    private boolean parse(Booking booking) {
        String[] parsed = condition.split(",");
        String[] subCondition;
        Criteria criteria;
        boolean status = true;
        for (String aParsed : parsed) {
            subCondition = aParsed.split(" ");
            criteria = conditions.get(subCondition[0]);
            if (criteria != null) {
                status = criteria.check(Integer.parseInt(subCondition[1]), booking);
            }
            if (!status) return false;
        }
        return true;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}