import { SimulationConfig } from "@common/types"
import constants from "src/common/constants"
import { useQuery } from "react-query"

const useSimulationConfig = () => {
    return useQuery<Map<string, SimulationConfig>, Error>('simulations', async () => {
        const response = await fetch(new URL(`simulations/config`, constants.api.url));
        
        if (!response.ok)
            throw new Error(`Code: ${response.status}`)

        const result = await response.json()
        return new Map(Object.entries(result))
    }, { enabled: true })
}

export default useSimulationConfig