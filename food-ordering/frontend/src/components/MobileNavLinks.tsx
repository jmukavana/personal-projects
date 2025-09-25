import {Link} from "react-router-dom";
import {Button} from "@/components/ui/button.tsx";


export default function MobileNavLinks() {
    return (
        <>
            <Link
                to={"/user-profile"}
                className={"flex bg-white items-center font-bold hover:text-orange-500"}>
                User profile
            </Link>
            <Button className={"flex px-3  items-center font-bold hover:bg-gray-500"}
            >
                Log Out
            </Button>
        </>
    )
}