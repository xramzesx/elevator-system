import { ElevatorStatus } from "@common/types"
import constants from "src/common/constants"
import { useQuery } from "react-query"

const useSimulationStatus = (simulationId: string) => useQuery<ElevatorStatus[], Error>(
    'simulationStatus', 
    async () => {
        const response = await fetch(new URL(`simulation/${simulationId}`, constants.api.url));
        
        if (!response.ok)
            throw new Error(`Code: ${response.status}`)

        const result = await response.json()
        return result as unknown as ElevatorStatus[]
    },{ 
        refetchInterval: 500 
    }
)

export default useSimulationStatus