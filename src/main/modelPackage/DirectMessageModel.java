package main.modelPackage;

import java.util.Date;

public class DirectMessageModel {

        private int id;

        private UserModel sender;


        private String text;

        private Date created_at;

      public DirectMessageModel( UserModel sender, String text) {
          this.sender = sender;
          this.text = text;
      }

        public UserModel getSender() {
            return sender;
        }

        public void setSender(UserModel sender) {
            this.sender = sender;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
}
