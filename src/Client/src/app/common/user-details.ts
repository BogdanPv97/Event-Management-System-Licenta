export class UserDetails {
  constructor(
    public userId: number,
    public firstName: string,
    public lastName: string,
    public email: string,
    public username: string,
    public role: string,
    public age: number,
    public newsletter: boolean,
    public city: string
  ) {}
}
