package model;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private float price;
	private String image;
	private String description;
	
	public Book(int id, String title, String author, float price,String img, 
				String desc) {
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.image = img;
		this.description = desc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price =price;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        }
        if (obj instanceof Book) {
        	Book other = (Book) obj;
            if (id != other.getId()) { return false; }
            if (!title.equals(other.getTitle())) { return false; }
            if (!author.equals(other.getAuthor())) { return false; }
            if (price != other.getPrice()) { return false; }
            if (!image.equals(other.getImage())) { return false; }
            if (!description.equals(other.getDescription())) { return false; }
            return true;
        }
        return false;
    }

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", image=" + image
				+ ", description=" + description + "]";
	}
}
