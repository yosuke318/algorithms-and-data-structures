class PlayfulPonyAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Pony(
                RandomWrapper.getRanDouble(0.5, 1.5),
                RandomWrapper.getRanDouble(35.0, 120.0),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
