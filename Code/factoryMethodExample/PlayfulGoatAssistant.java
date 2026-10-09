class PlayfulGoatAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Goat(
                RandomWrapper.getRanDouble(0.4, 1.0),
                RandomWrapper.getRanDouble(15.0, 60.0),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
