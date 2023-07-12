import { SimulationConfig } from "@common/types"
import constants from "src/common/constants"
import { useQuery } from "react-query"

const useSimulationConfig = (simulationId: string) => {
    return useQuery<SimulationConfig, Error>('simulationConfig', async () => {
        const response = await fetch(new URL(`simulation/${simulationId}/config`, constants.api.url));
        
        if (!response.ok)
            throw new Error(`Code: ${response.status}`)

        const result = await response.json()
        return result as unknown as SimulationConfig
    }, { enabled: true })
}

export default useSimulationConfig