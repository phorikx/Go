FROM node:14

# Create app directory
# WORKDIR /project
WORKDIR /

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json ./

EXPOSE 3000
EXPOSE 7070

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY . .


CMD ["npm install", "npm run start"]
# CMD npm run start

ENTRYPOINT npm run start
