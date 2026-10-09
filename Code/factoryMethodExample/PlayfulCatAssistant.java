class PlayfulCatAssistant extends PlayfulPetAssistant {
    @Override
    public PlayfulPet createPlayfulPet() {
        return new Cat(
                RandomWrapper.getRanDouble(0.15, 0.3),
                RandomWrapper.getRanDouble(2.0, 4.9),
                RandomWrapper.ranBoolean() ? "male" : "female"
        );
    }
}
