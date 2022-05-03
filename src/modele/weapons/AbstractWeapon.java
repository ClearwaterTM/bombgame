package modele.weapons;

/**
 * Cette classe est destinée à être hérité par {@link AbstractFixedWeapon} et {@link Shot}. Elle est destinée à représenter n'importe quelle arme.
 */
public abstract class AbstractWeapon {
    /**
     * Représente le nombre de dégâts infligés par l'arme.
     */
    protected int damage;

    /**
     * Constructeur de la classe.
     * @param damage nombre de dommages qu'inflige l'arme.
     */
    public AbstractWeapon(int damage) {
        this.damage = damage;
    }
}