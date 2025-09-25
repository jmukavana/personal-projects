import  mongoose from "mongoose";


export  default function ConnectToDB() {
    try {
        mongoose.connect(process.env.MONGODB_CONNECTION_STRING as string).
        then(r => console.log("Connected to MongoDB"));
    }catch (e) {
       throw Error("Failed to connect to MongoDB");
    }
}