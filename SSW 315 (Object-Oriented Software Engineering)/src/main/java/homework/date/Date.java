package homework.date;

/* Homework 2 */

class Date {

    /* Put your private data fields here. */
    private int month;
    private int day;
    private int year;

    /**
     * Constructs a date with the given month, day and year. If the date is not
     * valid, the entire program will halt with an error message.
     * 
     * @param month is a month, numbered in the range 1...12.
     * @param day   is between 1 and the number of days in the given month.
     * @param year  is the year in question, with no digits omitted.
     */
    public Date(int month, int day, int year) {

        if (isValidDate(month, day, year)) {
            this.month = month;
            this.day = day;
            this.year = year;
        } else {
            System.err.println("Invalid date");
            System.exit(0);
        }

    }

    /**
     * Constructs a Date object corresponding to the given string.
     * 
     * @param s should be a string of the form "month/day/year" where month must be
     *          one or two digits, day must be one or two digits, and year must be
     *          between 1 and 4 digits. If s does not match these requirements or is
     *          not a valid date, the program halts with an error message.
     */
    public Date(String s) {
        String[] dateArray = s.split("/");
        if (dateArray.length == 3) {
            int month = Integer.parseInt(dateArray[0]);
            int day = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            if (isValidDate(month, day, year)) {
                this.month = month;
                this.day = day;
                this.year = year;
            } else {
                System.err.println("Invalid date");
                System.exit(0);
            }
        }
    }

    /**
     * Checks whether the given year is a leap year.
     * 
     * @return true if and only if the input year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the number of days in a given month.
     * 
     * @param month is a month, numbered in the range 1...12.
     * @param year  is the year in question, with no digits omitted.
     * @return the number of days in the given month.
     */
    public static int daysInMonth(int month, int year) {
        if (Date.isLeapYear(year) && month == 2) {
            return 29;
        } else {
            switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid month");
            }
        }
    }

    /**
     * Checks whether the given date is valid.
     * 
     * @return true if and only if month/day/year constitute a valid date.
     *
     *         Years prior to A.D. 1 are NOT valid.
     */
    public static boolean isValidDate(int month, int day, int year) {
        if (year > 0) {
            if (month > 0 && month <= 12) {
                if (day > 0 && day <= daysInMonth(month, year)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a string representation of this date in the form month/day/year. The
     * month, day, and year are expressed in full as integers; for example,
     * 12/7/2006 or 3/21/407.
     * 
     * @return a String representation of this date.
     */
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year; // replace this line with your solution
    }

    /**
     * Determines whether this Date is before the Date d.
     * 
     * @return true if and only if this Date is before d.
     */
    public boolean isBefore(Date d) {
        if (this.year < d.year) {
            return true;
        } else if (this.year == d.year) {
            if (this.month <= d.month) {
                if (this.day < d.day) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines whether this Date is after the Date d.
     * 
     * @return true if and only if this Date is after d.
     */
    public boolean isAfter(Date d) {
        if (this.year > d.year) {
            return true;
        } else if (this.year == d.year) {
            if (this.month >= d.month) {
                if (this.day > d.day) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the number of this Date in the year.
     * 
     * @return a number n in the range 1...366, inclusive, such that this Date is
     *         the nth day of its year. (366 is used only for December 31 in a leap
     *         year.)
     */
    public int dayInYear() {
        int days = this.day;
        for (int i = 1; i < this.month; i++) {
            days += daysInMonth(i, this.year);
        }
        return days;
    }

    /**
     * Determines the difference in days between d and this Date. For example, if
     * this Date is 12/15/2012 and d is 12/14/2012, the difference is 1. If this
     * Date occurs before d, the result is negative.
     * 
     * @return the difference in days between d and this date.
     */
    public int difference(Date d) {
        int diff = 0;
        if (this.year == d.year) {
            diff = this.dayInYear() - d.dayInYear();
        } else {
            Date dateBefore;
            Date dateAfter;
            boolean negativeFlag = false;
            if (this.isAfter(d)) {
                dateAfter = this;
                dateBefore = d;
            } else {
                dateAfter = d;
                dateBefore = this;
                negativeFlag = true;
            }
            int daysInYear = 365;
            if (isLeapYear(dateBefore.year)) {
                daysInYear = 366;
            }
            diff += dateAfter.dayInYear() + (daysInYear - dateBefore.dayInYear());
            for (int i = dateBefore.year + 1; i < dateAfter.year; i++) {
                if (isLeapYear(i)) {
                    diff += 366;
                    continue;
                }
                diff += 365;
            }
            if (negativeFlag) {
                diff *= -1;
            }
        }
        return diff;

    }

}