interface PlayfulPet {
    String play();
    String playWithPerson(Person person);
    String playNoise();
    String getPetName();
    double getRentalCosts();
    boolean likesActivity(String activity);
    boolean dislikesActivity(String activity);
    String doActivity(String activity);
}
