package model;

import java.util.ArrayList;

public class Person
	{
		// name
		private String name;
		// date of birth
		private String birthDate;
		// date of death
		private String deathDate;
		// married
		private Boolean isMarried;
		// children
		private Boolean hasChildren;
		// age
		private int age;

		// constructor
		public Person()
			{
				name = "";
				birthDate = "unkown";
				deathDate = "unkown";
				isMarried = false;
				hasChildren = false;
				age = -500;
			}

		public Person(String name, String deathDate)
			{
				this.name = name;
				this.deathDate = deathDate;
			}

		
		public String toString()
			{
				String personInfo = "";

				personInfo += "This is: " + name;
				personInfo += "; died around: " + deathDate;
				personInfo += " at age: " + age;

				return personInfo;
			}
		
		// get/set

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String getBirthDate()
			{
				return birthDate;
			}

		public void setBirthDate(String birthDate)
			{
				this.birthDate = birthDate;
			}

		public String getDeathDate()
			{
				return deathDate;
			}

		public void setDeathDate(String deathDate)
			{
				this.deathDate = deathDate;
			}

		public Boolean getIsMarried()
			{
				return isMarried;
			}

		public void setIsMarried(Boolean isMarried)
			{
				this.isMarried = isMarried;
			}

		public Boolean getHasChildren()
			{
				return hasChildren;
			}

		public void setHasChildren(Boolean hasChildren)
			{
				this.hasChildren = hasChildren;
			}

		public int getAge()
			{
				return age;
			}

		public void setAge(int age)
			{
				this.age = age;
			}

		

	}
