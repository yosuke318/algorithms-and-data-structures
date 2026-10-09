class PlayfulHamsterAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Hamster(
                RandomWrapper.getRanDouble(0.08, 0.18),
                RandomWrapper.getRanDouble(0.1, 0.5),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
