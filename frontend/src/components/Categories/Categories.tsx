import { getBookCategories } from "../../api-calls/api";
import { useEffect, useState } from "react";


export default function Categories() {

    const [categories, setCategories] = useState<string[]>([]);
    
    useEffect(() => {
        getBookCategories().then((categories) => setCategories(categories));
    }, []);
    
    return (
        <div className="categories">
        <h2>Categories</h2>
        <ul>
            {categories.map((category) => (
            <li key={category}>{category}</li>
            ))}
        </ul>
        </div>
    );
    }