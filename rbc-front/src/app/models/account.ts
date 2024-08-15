// export class Account {
//     constructor(
//         public id: number | null,
//         public name: string,
//         public currency: string,
//         public balance: number
//     ){}
// }

export interface Account {
    id?: number;
    name: string;
    currency: string;
    balance: number;
}