package creational.code;

// Product
class User {
    // Required parameters
    private String firstName;
    private String lastName;
    // Optional parameters
    private int age;
    private String phone;
    private String address;

    public User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // All getter, and NO setter to provide immutability
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}


class UserBuilder {
    // Required parameters
    public String firstName;
    public String lastName;
    // Optional parameters
    public int age;
    public String phone;
    public String address;

    public UserBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder address(String address) {
        this.address = address;
        return this;
    }

    // Return the finally constructed User object
    public User build() {
        return new User(this);
    }
}

// Client
class SimpleBuilder{
    public static void main(String[] args) {
        User user1 = new UserBuilder("Salma", "Ayman")
        .age(30)
        .phone("1234567")
        .address("Fake address 1234")
        .build();
        
        System.out.println(user1.getFirstName());
        System.out.println(user1.getLastName());
        System.out.println(user1.getAge());
        System.out.println(user1.getPhone());
        System.out.println(user1.getAddress());

        User user2 = new UserBuilder("Nada", "Alassal")
        .age(40)
        .phone("5655")
        // no address
        .build();

        System.out.println(user2.getFirstName());
        System.out.println(user2.getLastName());
        System.out.println(user2.getAge());
        System.out.println(user2.getPhone());
        System.out.println(user2.getAddress());
    }
}