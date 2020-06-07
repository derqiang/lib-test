package me.daqiang.utils.enums;

/**
 * @ClassName EnumTest
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/12 4:55 下午
 * @Version 1.0
 **/
public class EnumTest {

    public static void testEnums() {
        for (Role r : Role.values()) {
            System.out.println(r + " ordinal:" + r.ordinal());
            System.out.println(r.compareTo(Role.DOCTOR) + " ");
            System.out.println(r.equals(Role.PATIENT) + " ");
            System.out.println(r == Role.DOCTOR);
            System.out.println(r.getDeclaringClass());
            System.out.println(r.name());
            System.out.println("============");
        }

        for (String r : "DOCTOR PATIENT".split(" ")) {
            Role role = Enum.valueOf(Role.class, r);
            System.out.println(role);
        }
    }

    public static void staticImportEnum() {

    }

    public static void testCustomeConstructor() {

        for (OzWitch r : OzWitch.values()) {
            System.out.println(r + " ordinal:" + r.ordinal());
            System.out.println(r.getDeclaringClass());
            System.out.println(r.name());
            System.out.println(r.getDes());
            System.out.println("============");
        }
    }

    public static void testSwitchEnums() {
        Role r = Role.DOCTOR;
        switch (r) {
            case DOCTOR: r = Role.PATIENT;
                break;
            case PATIENT: r = Role.DOCTOR;
                break;
            default:
                r = null;
        }
    }

}
