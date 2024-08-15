import { Account } from "./account";

export interface Transaction {
    id?: number,
    description: string,
    type: string,
    account: Account,
    amount: number,
    convertedAmount: number,
    currency: string
}
