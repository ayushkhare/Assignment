# Assignment
 - Code is written using Kotlin implementing MVVM architecture
 - Usage of life-cycle aware components like ViewModel and LiveData
 - Usage of fragments to load UI in MainActivity
 - Usage of view binding
 - Usage of retrofit2 for network calls
 
 ## Product Listing screen
  - Implemented using a fragment
  - Grid is displayed using RecyclerView and GridLayoutManager
  - Scroll listener added to RecyclerView to load the paginated api on scroll
  
      ### UI/UX
      - Inclusion of loading indicator at the bottom of RecyclerView to provide visual feedback while loading the next page of content
      #### BONUS
        - Added Swipe to refresh functionality to re-load the data on-demand
        - Used Floating Action Button to let the user scroll to top on a single tap since the list of products can be very long
        
 ## Product Detail screen
 - Implemented using a fragment
 - Implemented using a custom mutli-type recycler view that can take a heterogenous list of views and display in a single RecyclerView
 
      ### UI/UX
      - Include custom expandable text views for "Description" & "Ingredients" sections 
