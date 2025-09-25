import express,{Request,Response} from 'express';
import cors from 'cors';
import "dotenv/config";
import ConnectToDB from "./database/db_connection";
import CloudinaryConfig from "./cloudinary/CloudinaryConfig";
import myUserRoute from "./routes/MyUserRoute";
import myRestaurantRoute from "./routes/MyRestaurantRoute";
import restaurantRoute from "./routes/RestaurantRoute";
import orderRoute from "./routes/OrderRoute";

ConnectToDB();
CloudinaryConfig();
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());
app.use("/api/order/checkout/webhook", express.raw({ type: "*/*" }));


app.get("/health", async (req: Request, res: Response) => {
    res.send({ message: "health OK!" });
});

app.use("/api/my/user", myUserRoute);
app.use("/api/my/restaurant", myRestaurantRoute);
app.use("/api/restaurant", restaurantRoute);
app.use("/api/order", orderRoute);
let port =7000

app.listen(port,()=>console.log(`Server is running on port http://localhost:${port}`))

