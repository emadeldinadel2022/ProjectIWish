# i-Wish: Desktop Application Documentation

## Main Objectives

i-Wish is a desktop application designed to facilitate social gifting among friends. The application allows users to manage their friendships, create wishlists, view friends' wishlists, and contribute towards purchasing items for friends.

### Client-Server Application Basic Requirements

#### Client:

1. **Registration and Authentication:**
   - Registration form for new users.
   - Sign-in form with password reset functionality.
   - User authentication and access control.

2. **Friend Management:**
   - Add and remove friends.
   - Send and receive friend requests.
   - Accept or decline friend requests.
   - View friends list.

3. **Wishlist Management:**
   - Create, update, and delete wishlists.
   - View own wishlist.
   - View friends' wishlists.
   - Add, update, and remove items from wishlist.

4. **Item Contribution:**
   - Contribute money towards buying items from friends' wishlists.
   - Notification system for contributors and recipients.

#### Server:

1. **Admin Dashboard:**
   - Start and stop server.
   - Manage client connections and requests.
   - Database management.

2. **Database Management:**
   - Handle user registration and authentication.
   - Manage friendships and wishlists.
   - Handle item contributions and notifications.

### Database Design

#### Entities and Attributes:

1. **User:** user ID, username, email, password, balance, date of birth.
2. **Friendship:** user ID, friend ID, status.
3. **Wishlist:** wishlist ID, user ID, name, description.
4. **Item:** item ID, item name, price, description.
5. **Contribution/Gift:** contributor ID, recipient ID, item ID, amount, status.

#### Relationships:

1. User/Friend: many-to-many.
2. User/Friend Request: one-to-many.
3. User/Wishlist: one-to-one.
4. Wishlist/Item: many-to-many.

### Functional Requirements

#### Client:

1. **Registration/Sign-in:**
   - Secure user registration and sign-in.
   - Validation and error handling.

2. **User Profile Management:**
   - View wishlist, friends list, and friend requests.
   - Edit profile and log out.

3. **Friendship:**
   - Send, accept, and decline friend requests.
   - Remove friends from list.

4. **Wishlist:**
   - Create, view, and manage wishlists.
   - View friends' wishlists.

5. **Item Feed/List:**
   - View available items.
   - Add items to wishlist.

6. **Contribution and Notification:**
   - Contribute money towards gifts.
   - Receive notifications for completed contributions.

#### Server:

1. **Sign-in/Sign-up:**
   - Validate user credentials.
   - Manage user registration.

2. **Wishlist Management:**
   - Create, update, and delete wishlists.
   - Handle item additions and removals.

3. **Friendship Management:**
   - Send and handle friend requests.
   - Manage friends list.

4. **Contribution and Notification System:**
   - Handle contributions towards gifts.
   - Send notifications for completed contributions.

### Admin Dashboard

1. **Server Control:**
   - Start and stop server.
   - Monitor client connections.

2. **Database Management:**
   - Manage items, users, and friendships.

## Requirement Analysis and Design

### Database Design

- Define entities, attributes, and relationships.
- Implement SQL scripts for database schema.
- Optimize performance using PL/SQL and indexing.

### Process Workflows

- Registration workflow.
- Sign-in workflow.
- Friend request workflow.
- Wishlist management workflow.
- Item contribution and notification workflow.

## User Profile (Home)

- Wishlist management.
- Shopping.
- Friends list.
- Profile settings.

---
