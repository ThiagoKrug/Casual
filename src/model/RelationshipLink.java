package model;

public class RelationshipLink extends Model {

	private int id;
	private String link;
	private String name;
	private int averageDistance;
	private int minDistance;
	private int maxDistance;
	private int occurrenceNumber;

	public RelationshipLink() {
		this.occurrenceNumber = 0;
		this.minDistance = Integer.MAX_VALUE;
		this.maxDistance = Integer.MIN_VALUE;
	}

	public RelationshipLink(int id, String link, int distance,
			int occurrenceNumber, int minDistance, int maxDistance) {
		this.id = id;
		this.link = link;
		this.averageDistance = distance;
		this.occurrenceNumber = occurrenceNumber;
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(int averageDistance) {
		this.averageDistance = averageDistance;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public int getOccurrenceNumber() {
		return occurrenceNumber;
	}

	public void setOccurrenceNumber(int occurrenceNumber) {
		this.occurrenceNumber = occurrenceNumber;
	}

	public void addOccurrenceNumber() {
		this.occurrenceNumber++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\t\t[RelationshipLink: id: " + this.id + ", link: " + this.link
				+ ", averageDistance: " + this.averageDistance
				+ ", maxDistance: " + this.maxDistance + ", minDistance: "
				+ this.minDistance + ", occurenceNumber: "
				+ this.occurrenceNumber + ", name: " + this.name + "]\n";
	}
}
