package model;

import java.awt.Image;
import java.util.ArrayList;

public class GraveMarker
	{
		/**
		 * type of marker - String
		 */
		private String markerType;
		/**
		 * picture - Image
		 */
		private Image gravePicture;
		/**
		 * location - String or int on a grid
		 */
		private String location;
		/**
		 * people <> Strings in Arrays
		 */
		private ArrayList<Person> gravePersonList;
		/**
		 * information - String
		 */
		private String information;
		/**
		 * isReadable - boolean
		 */
		private Boolean isReadable;
		/**
		 * constructor - constructor of GraveMarker
		 */
		public GraveMarker()
			{
				markerType = "";
				location = "";
				gravePersonList = new ArrayList<Person>();
				information = "unkown";
				isReadable = false;
			}
			
	/**
	 * String method that gives a message of who is buried.	
	 */
	public String toString()
		{
			String graveInfo = "";
			
			for(Person current : gravePersonList)
				{
					graveInfo += current + " is buried here \n";
				}
			
			
			return graveInfo;
		}


	/**
	 * get/set - getters and setters
	 * @return
	 */
	public String getMarkerType()
		{
			return markerType;
		}

	public void setMarkerType(String markerType)
		{
			this.markerType = markerType;
		}

	public Image getGravePicture()
		{
			return gravePicture;
		}

	public void setGravePicture(Image gravePicture)
		{
			this.gravePicture = gravePicture;
		}

	public String getLocation()
		{
			return location;
		}

	public void setLocation(String location)
		{
			this.location = location;
		}

	public ArrayList<Person> getGravePersonList()
		{
			return gravePersonList;
		}

	public void setGravePersonList(ArrayList<Person> gravePersonList)
		{
			this.gravePersonList = gravePersonList;
		}

	public String getInformation()
		{
			return information;
		}

	public void setInformation(String information)
		{
			this.information = information;
		}

	public Boolean getIsReadable()
		{
			return isReadable;
		}

	public void setIsReadable(Boolean isReadable)
		{
			this.isReadable = isReadable;
		}
	
	}
