package library.model;

public abstract class LibraryResource{
        private int resourceId;
        private String title;
        private String author;

        private static String libraryName="Smart University Library";

        private static int resourceCount=0;

        public LibraryResource(int resourceId, String title, String author){
                this.resourceId=resourceId;
                this.title=title;
                this.author=author;
                resourceCount++;
        }
        public int getResourceId(){
                return resourceId;
        }
        public String getTitle(){
                return title;
        }
        public String getAuthor(){
                return author;
        }

        public void setResourceId(int resourceId){
                this.resourceId=resourceId;
        }
        public void setTitle(String title){
                this.title=title;
        }
        public void setAuthor(String author){
                this.author=author;
        }

        public static String getLibraryName(){
                return libraryName;
        }

        public static void setLibraryName(String libraryName){
                LibraryResource.libraryName=libraryName;
        }

        public static void displayTotalResources(){
                System.out.println("Total Resources Created: "+resourceCount);
        }

        protected void displayBasicDetails(){
                System.out.println("Resource ID: " + resourceId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
        }

        public abstract double calculateFine(int overdueDays);
}

