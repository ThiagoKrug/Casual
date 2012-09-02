package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class RelationshipDAO extends AbstractDAO {

	public RelationshipDAO(Connection connection) {
		super("relationship", connection);
	}

	@Override
	public void insert(Model model) {
		Relationship relationship = (Relationship) model;
		for (RelationshipLink relationshipLink : relationship
				.getRelationshipLinks()) {
			String sql = "insert into relationship (id_person1, id_person2, relationship_link, average_distance, occurrence_number, name, min_distance, max_distance) values (?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setInt(1, relationship.getPerson1().getId());
				stmt.setInt(2, relationship.getPerson2().getId());
				stmt.setString(3, relationshipLink.getLink());
				stmt.setInt(4, relationshipLink.getAverageDistance());
				stmt.setInt(5, relationshipLink.getOccurrenceNumber());
				stmt.setString(6, relationshipLink.getName());
				stmt.setInt(7, relationshipLink.getMinDistance());
				stmt.setInt(8, relationshipLink.getMaxDistance());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void insert(List<? extends Model> models) {
		for (Model model : models) {
			this.insert(model);
		}
	}

	@Override
	public void update(Model model) {
		Relationship relationship = (Relationship) model;
		for (RelationshipLink relationshipLink : relationship
				.getRelationshipLinks()) {
			String sql = "update person set id_person1=?, id_person2=?, relationship_link=?, average_distance=?, occurrence_number=?, name=?, min_distance=?, max_distance=? where id_relationship=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setInt(1, relationship.getPerson1().getId());
				stmt.setInt(2, relationship.getPerson2().getId());
				stmt.setString(3, relationshipLink.getLink());
				stmt.setInt(4, relationshipLink.getAverageDistance());
				stmt.setInt(5, relationshipLink.getOccurrenceNumber());
				stmt.setInt(6, relationshipLink.getId());
				stmt.setString(7, relationshipLink.getName());
				stmt.setInt(8, relationshipLink.getMinDistance());
				stmt.setInt(9, relationshipLink.getMaxDistance());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void delete(Model model) {
		Relationship relationship = (Relationship) model;
		for (RelationshipLink relationshipLink : relationship
				.getRelationshipLinks()) {
			String sql = "delete from person where id_relationship=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);

				stmt.setInt(1, relationshipLink.getId());

				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public List<Relationship> getRelationship(Person person) {
		return this.getRelationship(person.getId());
	}

	public List<Relationship> getRelationship(int idPerson) {
		String sql = "(select * from relationship where id_person1 = ?) "
				+ "UNION "
				+ "(select id_relationship, id_person2 as id_person1, id_person1 as id_person2, "
				+ "relationship_link, average_distance, occurrence_number, name, min_distance, "
				+ "max_distance from relationship where id_person2 = ?) "
				+ "order by 3";
		PersonDAO pdao = new PersonDAO(this.connection);
		try {
			ArrayList<Relationship> relationships = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, idPerson);
			stmt.setInt(2, idPerson);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Relationship relationship = new Relationship();
				RelationshipLink relationshipLink = new RelationshipLink();
				relationship
						.setPerson1(pdao.getPerson(rs.getInt("id_person1")));
				relationship
						.setPerson2(pdao.getPerson(rs.getInt("id_person2")));
				relationshipLink.setId(rs.getInt("id_relationship"));
				relationshipLink.setLink(rs.getString("relationship_link"));
				relationshipLink.setAverageDistance(rs.getInt("average_distance"));
				relationshipLink.setMinDistance(rs.getInt("min_distance"));
				relationshipLink.setMaxDistance(rs.getInt("max_distance"));
				relationshipLink.setOccurrenceNumber(rs.getInt("occurrence_number"));
				relationshipLink.setName(rs.getString("name"));
				relationship.addRelationshipLink(relationshipLink);

				while (rs.next()) {
					if (rs.getInt("id_person2") == relationship.getPerson2()
							.getId()) {
						RelationshipLink relationshipLink2 = new RelationshipLink();
						relationshipLink2.setId(rs.getInt("id_relationship"));
						relationshipLink2.setLink(rs
								.getString("relationship_link"));
						relationshipLink.setAverageDistance(rs.getInt("average_distance"));
						relationshipLink.setMinDistance(rs.getInt("min_distance"));
						relationshipLink.setMaxDistance(rs.getInt("max_distance"));
						relationshipLink2.setOccurrenceNumber(rs.getInt("occurrence_number"));
						relationshipLink.setName(rs.getString("name"));
						relationship.addRelationshipLink(relationshipLink2);
					} else {
						rs.previous();
						break;
					}
				}

				relationships.add(relationship);
			}

			rs.close();
			stmt.close();
			return relationships;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
