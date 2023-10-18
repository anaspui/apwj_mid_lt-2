import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Employee {
    private final String empName;
    private final Date empJoiningDate;
    private final Date empDateOfBirth;
    private final String empEmail;
    private int empVacationLeave;
    private int empSickLeave;
    private final int empType;

    public Employee(String name, Date joiningDate, Date dateOfBirth, String email, int type) {
        this.empName = name;
        this.empJoiningDate = joiningDate;
        this.empDateOfBirth = dateOfBirth;
        this.empEmail = email;
        this.empType = type;
        calculateEmpLeave();
    }

    public String getName() {
        return empName;
    }

    public Date getJoiningDate() {
        return empJoiningDate;
    }

    public Date getDateOfBirth() {
        return empDateOfBirth;
    }

    public String getEmail() {
        return empEmail;
    }

    public int getVacationLeave() {
        return empVacationLeave;
    }

    public int getSickLeave() {
        return empSickLeave;
    }

    private void calculateEmpLeave() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 11, 31);
        int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366) {
            totalDays = 366;
        }

        long daysWorked = (calendar.getTimeInMillis() - this.empJoiningDate.getTime()) / (24 * 60 * 60 * 1000) + 1;

        if (isEmpOfficer()) {
            empVacationLeave = calculateOfficerVacationLeave(daysWorked, totalDays);
            empSickLeave = calculateOfficerSickLeave(daysWorked, totalDays);
        } else {
            empVacationLeave = calculateStaffVacationLeave(daysWorked, totalDays);
            empSickLeave = calculateStaffSickLeave(daysWorked, totalDays);
        }
    }

    private int calculateOfficerVacationLeave(long daysWorked, int totalDays) {
        double leaveFraction = (daysWorked * 15) / (double) totalDays;

        if (leaveFraction < 0.5) {
            return (int) Math.floor(leaveFraction);
        } else {
            return (int) Math.ceil(leaveFraction);
        }
    }

    private int calculateOfficerSickLeave(long daysWorked, int totalDays) {
        double leaveFraction = (daysWorked * 10) / (double) totalDays;

        if (leaveFraction < 0.5) {
            return (int) Math.floor(leaveFraction);
        } else {
            return (int) Math.ceil(leaveFraction);
        }
    }

    private int calculateStaffVacationLeave(long daysWorked, int totalDays) {
        double leaveFraction = (daysWorked * 10) / (double) totalDays;

        if (leaveFraction < 0.5) {
            return (int) Math.floor(leaveFraction);
        } else {
            return (int) Math.ceil(leaveFraction);
        }
    }

    private int calculateStaffSickLeave(long daysWorked, int totalDays) {
        double leaveFraction = (daysWorked * 7) / (double) totalDays;

        if (leaveFraction < 0.5) {
            return (int) Math.floor(leaveFraction);
        } else {
            return (int) Math.ceil(leaveFraction);
        }
    }

    private boolean isEmpOfficer() {
        return switch (empType) {
            case 1 -> true;
            case 2 -> false;
            default -> false;
        };
    }

    public static Date parseDate(String dateString, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}